#ifndef Menu_h
#define Menu_h

namespace M
{
	enum WszystkieAkcje
	{
		AKCJE_GLOWNE,
		INSTRUMENTY,
		RAPORTY
	};
	enum AkcjeGlowne
	{
		WyemitujInstrument,
		ZaproponujUmowe,
		ZawrzyjUmowe,
		ObejrzyjRaporty,
		MaxAkcjeGlowne,
	};
	enum Instrumenty
	{
		Obligacje,
		Pozyczki,
		Akcje,
		Dowolne,
		MaxInstrumenty
	};
	enum Raporty
	{
		RaportTraderow,
		RaportBanku,
		RaportKorporacji,
		RaportPrzeplywowNaRynku,
		MaxRaporty
	};
}


class Menu
{
public:
	Menu(void);
	Menu(const std::vector <int> &options, M::WszystkieAkcje typ, int sublevel);
	//full menu constructor
	Menu(M::WszystkieAkcje typ, int sublevel);

	~Menu(void);
	void Display();
	int Ask4Option();
	void Activate();
	void Deactivate();
	void SubLevelPrint();
	void RemoveOption(int option);
	std::vector <int> * getOptions(){ return &options_;}


private:
	M::WszystkieAkcje typ_;
	int sublevel_;
	std::vector <int> options_;
	std::vector <std::string> options_str;
	bool active_;
};

#endif
