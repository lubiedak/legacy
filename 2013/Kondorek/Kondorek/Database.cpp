#include "stdafx.h"
#include "Database.h"

static int callback(void *NotUsed, int argc, char **argv, char **azColName){
    for(int i=0; i < argc; ++i)
    {
        std::cout << argv[i] ? std::string(argv[i]) : "NULL";
        std::cout << " | ";
    }
    std::cout << std::endl;
    return 0;
}
std::string ci2str(int n)
{
    std::string str;
    std::ostringstream convert;
    convert << n;
    return convert.str();
}

Database::Database(void)
{
}

Database::Database(std::string const &db_path)
{
    db_path_ = db_path;
    int rc = sqlite3_open(db_path_.c_str(), &db);
    if( rc )
    {
        std::cout<<"Can't open database: "<<db_path_<<std::endl;
        sqlite3_close(db);
        opened = false;
    }
    else
    {
        opened = true;
    }

    table_name_ = "Books";
    command_ = "SELECT * from Traderzy;";
    runQuery();
}

Database::~Database(void)
{
}

bool Database::Ask4AR(int ar, const User *user)
{
	return true;
}

bool Database::Ask4User(std::string l,std::string p )
{
	table_name_ = "UzytkownicySystemu";
	command_ = "SELECT COUNT(*) from " + table_name_ + " WHERE UserName = \""+l+"\" AND Password = \""+p+"\";";
	int i = GetValueInt();
	sqlite3_close(db);
	if( i != -1 && i != 0)
	{
		return true;
	}
	return false;
}


int Database::GetValueInt(void)
{
	int result = -1;
	try
    {
		sqlite3_stmt *ppStmt = 0;
		const char *pzTail = 0;

		openConnection();
		
        std::cout<<command_<<std::endl;
		if( sqlite3_prepare(db, command_.c_str(), -1, &ppStmt, 0 ) == SQLITE_OK)
		{
			int rc = sqlite3_step(ppStmt);
			if(rc == SQLITE_ROW)
			{
				return sqlite3_column_int(ppStmt, 0);
			}
			else
			{
				return -1;
			}
		}
    }
    catch(const DBError& ex)
    {
        std::cout << "Database error: " << ex.what() << std::endl;
    }
    catch(const std::exception& ex)
    {
        std::cout << "Exception:" << ex.what() << std::endl;
    }
	return result;
}

void Database::openConnection()
{
    if( sqlite3_open(db_path_.c_str(), &db) ){
      std::string error = std::string("Can't open database: ") + sqlite3_errmsg(db);
      sqlite3_close(db);
      throw DBError(error);
    }
}

void Database::runCommand()
{
    char *zErrorMsg = 0;
    if( sqlite3_exec(db, command_.c_str(), callback, 0, &zErrorMsg) != SQLITE_OK )
    {
        std::string error_message = std::string("SQL error: ") + zErrorMsg;
        sqlite3_free(zErrorMsg);
        throw DBError(error_message);
    }
    sqlite3_close(db);
}

void Database::runQuery()
{
    try
    {
        std::cout<<command_<<std::endl;
        openConnection();
        runCommand();
    }
    catch(const DBError& ex)
    {
        std::cout << "Database error: " << ex.what() << std::endl;
    }
    catch(const std::exception& ex)
    {
        std::cout << "Exception:" << ex.what() << std::endl;
    }

    command_ = "";
}
