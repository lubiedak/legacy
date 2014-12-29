#include "stdafx.h"
#include "Logger.h"

using namespace std;
Logger::Logger(void)
{

}


Logger::~Logger(void)
{

}



void Logger::Ask4Login(void)
{
	string login = "";
	cout<<"Please enter\nLogin: ";
	cin>>login;
	login_ = login;
}

void Logger::Ask4Password(void)
{
	string password = "";
	cout<<"Please enter\nPassword: ";
	cin>>password;
	pass_ = password;
}

bool Logger::VerifyUser()
{
	Ask4Login();
	Ask4Password();
	TranslatePassword();
		
	return db_->Ask4User(login_, pass_);
	//ask to db
}

//fake function to translate password
string Logger::TranslatePassword()
{
	pass_ = "XXXXXXXX";
	return pass_;
}