using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.UI.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI
{
    public class AdUI : IAdUI
    {
        private readonly IAdBL _iAdBL;
        public AdUI(IAdBL iAdBL)
        {
            _iAdBL = iAdBL;
        }

        public int AddNewAd(string title, int flat_house, int sell_rent, string description, double size, String date_start, double price, string location)
        {
            return _iAdBL.AddNewAd(title, flat_house, sell_rent, description, size, date_start, price, location);
        }
    }
}
