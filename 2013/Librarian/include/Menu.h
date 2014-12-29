#ifndef MENU_H
#define MENU_H
#include <string>
#include <map>
#include <vector>
#include <stdlib.h>
#include "../include/Database.h"


namespace M{
	enum
	{
	    disp = 0,
		edit,
		add,
		rm,
		fnd,
		srt,
		exit,
		menu_size
	};
}

class Menu
{
    public:
        Menu();

        virtual ~Menu();
        std::string Ask4Database() const;
        void Ask4Option(bool sub = false);

        Book AddBook();
        Book EditBook();
        int RemoveBook();
        std::string SortBooks();
        std::string SearchBooks(std::string &what);
        void MainLoop();
        void DisplayMenu();
        void RunOption();
        void DisplaySubMenu() const;
    protected:
    private:
        std::vector< std::string > options;
        std::vector< std::string > sub_options;
        std::vector< bool > has_sub_option;
        std::string header_;
        std::string ask4db_;
        int selected_option;
        int selected_suboption;
        Database db_;
        bool ready_;

};

#endif // MENU_H
