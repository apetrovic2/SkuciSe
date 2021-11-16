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

        public int AddNewAd(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv)
        {
            return _iAdBL.AddNewAd(title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
        }

        public int CloseAd(int id, String date_end)
        {
            return _iAdBL.CloseAd(id, date_end);
        }

        public int DeleteAd(int id)
        {
            return _iAdBL.DeleteAd(id);
        }

        public int EditAd(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv)
        {
            return _iAdBL.EditAd(id, title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
        }
    }
}
