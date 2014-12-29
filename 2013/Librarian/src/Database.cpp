#include "../include/Database.h"


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

Database::Database()
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
    table_name = "Books";
    command = "CREATE TABLE IF NOT EXISTS " + table_name + "(Id INTEGER, Author TEXT , Title TEXT, Year INTEGER);";
    runQuery();
}

void Database::AddRecord( Book b)
{
    command = "INSERT INTO " + table_name + " VALUES(";
    command += ci2str(b.getID()) + ",\"";
    command += b.getAuthor() + "\",\"";
    command += b.getTitle() + "\",";
    command += ci2str(b.getYear()) + ");";
    runQuery();
}

void Database::EditRecord( Book b)
{
    command = "UPDATE " + table_name + " SET";
    command += " Author=\"" + b.getAuthor() + "\", ";
    command += " Title=\"" + b.getTitle() + "\", ";
    command += " Year=" + ci2str(b.getYear());
    command += " WHERE Id =" + ci2str(b.getID());
    runQuery();
}

void Database::RemoveRecord( int id )
{
    command = "DELETE FROM " + table_name + " WHERE ID=" + ci2str(id) + ";";
    runQuery();
}
void Database::SearchRecords(std::string const &where, std::string const &what)
{
    command = "SELECT * FROM " + table_name + " WHERE "+ where + "=" + what + ";";
    runQuery();
}

void Database::DisplaySorted(std::string const &order_by)
{
    command = "SELECT * FROM " + table_name + " ORDER BY " + order_by + ";";
    runQuery();
}

void Database::DisplayRecords()
{
    command = "SELECT * FROM " + table_name;
    runQuery();
}


Database::~Database()
{
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
    if( sqlite3_exec(db, command.c_str(), callback, 0, &zErrorMsg) != SQLITE_OK )
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
        std::cout<<command<<std::endl;
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

    command = "";
}
