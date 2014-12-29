#include "stdafx.h"
#include "Menu.h"
#include "MenuTexts.h"

using namespace std;
Menu::Menu(void)
{
}
Menu::Menu(const vector <int> &options, M::WszystkieAkcje typ, int sublevel)
{
	sublevel_ = sublevel;
	options_ = options;
	active_ = false;
	typ_ = typ;
	MenuTexts texts;
	for(vector<int>::iterator it = options_.begin();
		it != options_.end();
		++it)
	{
		switch (typ)
		{
			case M::AKCJE_GLOWNE:
				options_str.push_back(texts.Glowne_str[*it]);
				break;
			case M::INSTRUMENTY:
				options_str.push_back(texts.Instrumenty_str[*it]);
				break;
			case M::RAPORTY:
				options_str.push_back(texts.Raporty_str[*it]);
				break;
		}
	}
}

//full menu constructor
Menu::Menu(M::WszystkieAkcje typ, int sublevel)
{
	sublevel_ = sublevel;
	active_ = false;
	MenuTexts texts;
	typ_ = typ;

	switch(typ)
	{
		case M::AKCJE_GLOWNE:
			options_str = texts.Glowne_str;
			options_ = texts.Glowne_int;
			break;
		case M::INSTRUMENTY:
			options_str = texts.Instrumenty_str;
			options_ = texts.Instrumenty_int;
			break;
		case M::RAPORTY:
			options_str = texts.Raporty_str;
			options_ = texts.Raporty_int;
			break;
	}
}

Menu::~Menu(void)
{
}

void Menu::Activate()
{
	active_ = true;
}

void Menu::Deactivate()
{
	active_ = false;
}

void Menu::Display()
{
	if(active_)
	{
		for( unsigned int i = 0; i < options_str.size(); ++i)
		{
			SubLevelPrint();
			cout<<(i+1)<<". "<<options_str[i]<<endl;
		}
	}
}

int Menu::Ask4Option()
{
	int wybor = -1;
	cout<<"Wybierz jedna z dostepnych opcji: ";
	do
	{
		char opcja = _getch();
		cout<<opcja;
		bool blad = true;
		for(vector<int>::iterator it = options_.begin(); it != options_.end(); ++it)
		{
			if (opcja == *it+48 )
			{
				blad = false;
			}
		}

		if(!blad)
		{
			wybor = options_[opcja - 48];

			cout<<"\n";
			SubLevelPrint();
			cout<<opcja<<". "<<options_str[wybor]<<endl;
		}
		else
		{
			cout<<"\nNIEDOSTEPNA OPCJA!!"<<endl;
		}
	}while((_kbhit()));
	return wybor;
}

void Menu::SubLevelPrint()
{
	for(int j = 0; j<=sublevel_; j++)
	{
		cout<<"   ";
	}
}

void Menu::RemoveOption(int option)
{
	std::vector<int>::const_iterator to_remove_int;
	std::vector<string>::const_iterator to_remove_str;

	std::vector<string>::iterator its = options_str.begin();

	for(std::vector<int>::iterator it = options_.begin(); it != options_.end(); ++it)
	{
		its++;
		if (*it == option)
		{
			to_remove_int = it;
			to_remove_str = its;
		}
	}
	options_.erase(to_remove_int);
	options_str.erase(to_remove_str);
}
