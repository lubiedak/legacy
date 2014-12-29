#ifndef MenuTexts_h
#define MenuTexts_h


class MenuTexts
{
public:
	MenuTexts(void);
	~MenuTexts(void);
	std::vector<std::string> Glowne_str;
	std::vector<std::string> Instrumenty_str;
	std::vector<std::string> Raporty_str;


	std::vector<int> Glowne_int;
	std::vector<int> Instrumenty_int;
	std::vector<int> Raporty_int;

};

#endif