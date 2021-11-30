using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.Models;
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

        public Task<AdWithImage> GetAdById(int id)
        {
            return _iAdBL.GetAdById(id);
        }

        public Task<List<AdWithImage>> GetAllAds(int category)
        {
            return _iAdBL.GetAllAds(category);
        }

        public int AddNewAd(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id)
        {
            return _iAdBL.AddNewAd(title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv, user_id);
        }

        public int CloseAd(int id, String date_end)
        {
            return _iAdBL.CloseAd(id, date_end);
        }

        public int DeleteAd(int id)
        {
            return _iAdBL.DeleteAd(id);
        }

        public int EditAd(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id)
        {
            return _iAdBL.EditAd(id, title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv, user_id);
        }

        public Task<List<AdWithImage>> GetAdsByUserId(int user_id)
        {
            return _iAdBL.GetAdsByUserId(user_id);
        }

        public Task<List<AdWithImage>> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, string location, int internet, int ac, int heating, int tv)
        {
            return _iAdBL.FilterAds(sell_rent, flat_house, from_number_of_rooms, to_number_of_rooms, from_size, to_size, from_price, to_price, location, internet, ac, heating, tv);
        }

        public int SetAdPicture(int ad_id, String image)
        {
            return _iAdBL.SetAdPicture(ad_id, image);
        }

        public int MakeAnAppointment(int user_id, int ad_id, string date)
        {
            return _iAdBL.MakeAnAppointment(user_id, ad_id, date);
        }

        public Task<List<AppointmentModel>> GetAppointmentByOwnerId(int id)
        {
            return _iAdBL.GetAppointmentByOwnerId(id);
        }

        public int ApproveAppointment(int app_id)
        {
            return _iAdBL.ApproveAppointment(app_id);
        }
    }
}
