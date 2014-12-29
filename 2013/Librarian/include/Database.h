#ifndef DATABASE_H
#define DATABASE_H
#include <string>
#include "../sqlite/sqlite3.h"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "../include/Book.h"
#include <sstream>

namespace DB{
	enum
	{
		id = 0,
		author,
		title,
		year,
		db_fields
	};
}

const std::string book_fields[] = {"Id ", "Author ", "Title ", "Year "};
const int VARCHAR_MAX = 255;
//static int callback(void *NotUsed, int argc, char **argv, char **azColName);
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
        void EditRecord( Book b );
        void AddRecord( Book b );
        void RemoveRecord( int id );
        void DisplayRecords();
        void DisplaySorted(std::string const &order_by);
        void SearchRecords(std::string const &where, std::string const &what);

        bool opened;

        void openConnection();
        void runCommand();
        void runQuery();
    protected:
    private:
        std::string command;
        std::string db_path_;
        sqlite3 *db;
        std::string table_name;

};
#endif // DATABASE_H
