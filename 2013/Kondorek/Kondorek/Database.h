#ifndef Database_h
#define Database_h

#include "sqlite/sqlite3.h"
#include <sstream>
#include "User.h"

static const int VARCHAR_MAX = 255;

class Database
{
public:

    class DBError : public std::exception
    {
    public:

        DBError(const std::string& s) : msg(s)
        {
        }

        virtual const char* what() const throw () { return msg.c_str(); }
        virtual ~DBError() throw () { }
    private:
        std::string msg;
    };

    Database();
    Database(std::string const &path);
    virtual ~Database();
	bool Ask4AR(int ar, const User *user);
	/*
    void EditRecord( Book b );
    void AddRecord( Book b );
    void RemoveRecord( int id );
    void DisplayRecords();
    void DisplaySorted(std::string const &order_by);
    void SearchRecords(std::string const &where, std::string const &what);
	*/
    bool opened;
	bool Ask4User(std::string l,std::string p );
    

private:
	void openConnection();
    void runCommand();
    void runQuery();
	int GetValueInt(void);
    std::string command_;
    std::string db_path_;
    sqlite3 *db;
    std::string table_name_;

};
#endif // DATABASE_H