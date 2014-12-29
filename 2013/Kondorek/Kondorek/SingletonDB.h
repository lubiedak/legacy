#ifndef Singleton_h
#define Singleton_h
#include "Database.h"


class SingletonDB
{
   public:
	   static Database* getInstance( void ){return instance;}
       ~SingletonDB( void );
   private:
       SingletonDB( void );
       static Database* instance;
};

#endif
