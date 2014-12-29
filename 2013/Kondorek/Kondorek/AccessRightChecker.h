#ifndef AccessRightChecker_h
#define AccessRightChecker_h

#include "Menu.h"
#include "User.h"

class AccessRightChecker
{
public:
	AccessRightChecker(void);
	AccessRightChecker(Database &db, const User &user);
	~AccessRightChecker(void);
	Menu CreateMenu(M::WszystkieAkcje typ);
	bool Ask4AR(int ar);

private:
	Database * db_instance_;
	const User * user_;

};
#endif
