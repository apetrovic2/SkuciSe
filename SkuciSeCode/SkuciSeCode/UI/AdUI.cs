using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.Entities;
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

        public Ad GetAdById(int id)
        {
            return _iAdBL.GetAdById(id);
        }

        public List<Ad> GetAllAds(int category)
        {
            return _iAdBL.GetAllAds(category);
        }

        public int AddNewAd(string title, int flat_house, int sell_rent, string description, float size, String date_start, float price, string location)
        {
            return _iAdBL.AddNewAd(title, flat_house, sell_rent, description, size, date_start, price, location);
        }

        public int CloseAd(int id, String date_end)
        {
            return _iAdBL.CloseAd(id, date_end);
        }

        public int DeleteAd(int id)
        {
            return _iAdBL.DeleteAd(id);
        }
    }
}
