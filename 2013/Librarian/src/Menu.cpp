#include "../include/Menu.h"


Menu::Menu()
{
    header_ = "::MENU::";
    ask4db_ = "Enter path to database file: ";
    options.resize(M::menu_size);
    has_sub_option.resize(M::menu_size);
    options[M::disp] = " Display database";
    options[M::edit] = " Edit database entry";
    options[M::add] =  " Add new book to the library";
    options[M::rm] =   " Remove book from the library";
    options[M::fnd] =  " Search for a book";
    has_sub_option[M::fnd] = true;
    options[M::srt] =  " Sort and display books";
    has_sub_option[M::srt] = true;
    options[M::exit] =  " Exit";

    sub_options.resize(DB::db_fields);

    sub_options[DB::id] =        " By book ID";
    sub_options[DB::author] =    " By author";
    sub_options[DB::title] =     " By title";
    sub_options[DB::year] =      " By year";
    selected_option = 0;
    ready_ = false;
}

Menu::~Menu()
{
    //dtor
}

void Menu::DisplayMenu()
{
    std::cout<<"\n::MENU::"<<std::endl;

    bool suboption = false;
    for(int i=0; i<M::menu_size; ++i)
    {
        std::cout<<i<<options[i]<<std::endl;
    }
    Ask4Option();
    if (has_sub_option[selected_option])
    {
        DisplaySubMenu();
        Ask4Option(has_sub_option[selected_option]);
    }
        RunOption();

}

void Menu::RunOption()
{
    switch(selected_option)
    {
        case M::disp:
            db_.DisplayRecords();
            break;
        case M::edit:
            db_.DisplayRecords();
            db_.EditRecord(EditBook());
            break;
        case M::add:
            db_.AddRecord(AddBook());
            break;
        case M::rm:
            db_.DisplayRecords();
            db_.RemoveRecord(RemoveBook());
            break;
        case M::fnd:
            {
                std::string what;
                std::string where;
                where = SearchBooks(what);
                db_.SearchRecords(where, what);
            }
            break;
        case M::srt:
            db_.DisplaySorted(SortBooks());
            break;
    }
}

void Menu::DisplaySubMenu() const
{
    for(int i=0; i<DB::db_fields; ++i)
    {
        std::cout<<"  "<<i<<sub_options[i]<<std::endl;
    }
}

Book Menu::AddBook()
{
    Book b = Book();
    std::string fields[DB::db_fields];
    for (int i = 0; i < DB::db_fields; ++i)
    {
        std::cout<<"Enter "<<book_fields[i];
        getline(std::cin, fields[i]);
    }
    std::cin.ignore();
    b.setID( atoi( fields[DB::id].c_str() ) );
    b.setAuthor( fields[DB::author] );
    b.setTitle( fields[DB::title] );
    b.setYear( atoi( fields[DB::year].c_str() ) );
    return b;
}

Book Menu::EditBook()
{
    std::cout<<"Enter ID of book which you want to edit: ";
    int id;
    std::cin>>id;
    std::cin.ignore();
    Book b = Book();
    std::string fields[DB::db_fields];
    for (int i = 1; i < DB::db_fields; ++i)
    {
        std::cout<<"Enter "<<book_fields[i];
        getline(std::cin, fields[i]);
    }
    std::cin.ignore();
    b.setID( id );
    b.setAuthor( fields[DB::author] );
    b.setTitle( fields[DB::title] );
    b.setYear( atoi( fields[DB::year].c_str() ) );
    return b;
}

int Menu::RemoveBook()
{
    std::cout<<"Enter ID of book which you want to remove: ";
    int id;
    std::cin>>id;
    std::cin.ignore();
    return id;
}

std::string Menu::SortBooks()
{
    return book_fields[selected_suboption];
}

std::string Menu::SearchBooks(std::string &what)
{
    std::cout<<"Enter what do you want to find: ";
    getline(std::cin, what);
    if (selected_suboption == DB::author || selected_suboption == DB::title)
        what = "\""+what+"\"";
    std::cin.ignore();
    return book_fields[selected_suboption];
}

void Menu::Ask4Option(bool sub)
{
    int max_option = M::menu_size;
    bool option_chosen = false;
    while(!option_chosen)
    {
        if(sub)
        {
            max_option = DB::db_fields;
            std::cout<<"  Choose suboption from submenu: ";
        }
        else
        {
            std::cout<<"Choose option from menu: ";
        }

        std::string str_opt;
        std::cin>>str_opt;
        std::cin.ignore();
        /*try
        {*/
            int option = atoi( str_opt.c_str() ); //brak std::stoi w bibliotece:/
            if ( option >= 0 && option < max_option )
            {
                if(sub)
                {
                    selected_suboption = option;
                }
                else
                {
                    selected_option = option;
                }
                option_chosen = true;
            }
            else
            {
                option_chosen = false;
                std::cout<<"You chose wrong option, try again"<<std::endl;
            }
        //}
        /*catch (std::invalid_argument const &e)
        {
            return false;
        }*/
    }
}

std::string Menu::Ask4Database() const
{
    std::string db_path;
    std::cout<<"Select database file: ";
    std::cin>>db_path;
    return db_path;
}

void Menu::MainLoop()
{
    std::string db_path = Ask4Database();
    db_ = Database(db_path);

    if (db_.opened)
    {
        do
        {
            DisplayMenu();

        }while(selected_option != M::exit);
    }

    std::cout<<"Bye, bye"<<std::endl;

}

