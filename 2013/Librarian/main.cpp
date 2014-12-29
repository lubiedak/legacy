#include <stdio.h>
#include "sqlite/sqlite3.h"
#include "include/Book.h"
#include "include/Database.h"
#include "include/Library.h"
#include "include/Menu.h"


int main(int argc, char *argv[])
{
    Menu m;
    m.MainLoop();
    
    return 0;
}
