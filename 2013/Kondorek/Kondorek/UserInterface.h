#ifndef UserInterface_h
#define UserInterface_h

#include "Menu.h"

class UserInterface
{
public:
	UserInterface(void);
	~UserInterface(void);
	void Run(void);

private:
	std::vector <Menu> MenuFlow;

};
#endif
