#include "stdafx.h"
#include "AccessRightChecker.h"


AccessRightChecker::AccessRightChecker(void)
{
}

AccessRightChecker::AccessRightChecker(Database &db, const User &user)
{
	db_instance_ = &db;
	user_ = &user;
}


AccessRightChecker::~AccessRightChecker(void)
{
}

Menu AccessRightChecker::CreateMenu(M::WszystkieAkcje typ)
{
	Menu fullmenu = Menu(typ, 0);
	for (unsigned int i = 0; i < fullmenu.getOptions()->size(); i++)
	{
		if(!Ask4AR(i))
		{
			fullmenu.RemoveOption(i);
		}
	}
	return fullmenu; //removed unavailable options
}


bool AccessRightChecker::Ask4AR(int ar)
{
	db_instance_->Ask4AR(ar, user_);
	return true;
}
