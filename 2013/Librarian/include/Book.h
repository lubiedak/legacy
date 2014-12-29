#ifndef BOOK_H
#define BOOK_H
#include <string>

class Book
{
    public:
        Book();
        virtual ~Book();

        int getID() const{ return ID_; }
        std::string getAuthor() const{ return author_; }
        std::string getTitle()  const{ return title_; }
        int getYear() const{ return year_; }

        void setID( int new_id ) { ID_ = new_id; }
        void setAuthor( const std::string &new_a ) { author_ = new_a; }
        void setTitle( const std::string &new_t ) { title_ = new_t; }
        void setYear( int new_y ) { year_ = new_y; }

    protected:
    private:
        int ID_;
        std::string author_;
        std::string title_;
        int year_;
};

#endif // BOOK_H
