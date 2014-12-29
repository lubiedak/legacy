#include "stdafx.h"
#include "MenuTexts.h"


MenuTexts::MenuTexts(void)
{
	
	Glowne_str.push_back("Wyemituj Instrument");
	Glowne_str.push_back("Zaproponuj Umowe");
	Glowne_str.push_back("Zawrzyj Umowe");
	Glowne_str.push_back("Obejrzyj Raporty");
	for(int i=0; i<4;i++)
	{
		Glowne_int.push_back(i);
	}

	Instrumenty_str.push_back("Obligacje");
	Instrumenty_str.push_back("Pozyczki");
	Instrumenty_str.push_back("Akcje");
	for(int i=0; i<3;i++)
	{
		Instrumenty_int.push_back(i);
	}

	Raporty_str.push_back("Raport Traderow");
	Raporty_str.push_back("Raport Banku");
	Raporty_str.push_back("RaportTraderow Korporacji");
	Raporty_str.push_back("Raport Przeplywów na rynku");
	for(int i=0; i<4;i++)
	{
		Raporty_int.push_back(i);
	}
}


MenuTexts::~MenuTexts(void)
{
}
