#ifndef Logger_h
#define Logger_h


class Logger
{
public:
	Logger(void);
	Logger(Database &db){db_ = &db;}
	~Logger(void);
	void Ask4Login(void);
	void Ask4Password(void);
	bool VerifyUser();

private:
	
	std::string TranslatePassword(void);
	std::string login_;
	std::string pass_;
	Database * db_;
};

#endif