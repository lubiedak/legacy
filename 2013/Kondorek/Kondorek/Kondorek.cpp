
#include "stdafx.h"
#include "Menu.h"
#include "Logger.h"


std::string Ask4Database();
void MainLoop();

int main(int argc, char* argv[])
{

	/******************
	Do klasy Database dopisaæ kilka funkcji do wyciagania AR,
	Dorobiæ funkcje do displayu UI
	Zalozyc klase odpowiedzialna za wsadzanie deali

	*****************/

	MainLoop();

	return 0;
}

std::string Ask4Database()
{
    std::string db_path;
    std::cout<<"Select database file: ";
    std::cin>>db_path;
    return db_path;
}

void MainLoop()
{
    std::string db_path = Ask4Database();
    Database db_ = Database(db_path);

    if (db_.opened)
    {
		Logger log(db_);
		for(int tries=3; tries>0; tries--)
		{
			if (log.VerifyUser())
			{
				std::cout<<"\nUser verification passed.";
				tries = 0;

			}
			else
			{
				std::cout<<"\nUser verification failed. "<<tries<<" tries left.\n";
			}
		}
    }

    std::cout<<"Bye, bye"<<std::endl;

}

