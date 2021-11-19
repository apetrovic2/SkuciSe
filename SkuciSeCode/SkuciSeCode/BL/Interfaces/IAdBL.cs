using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL.Interfaces
{
    public interface IAdBL
    {
        List<Ad> GetAllAds(int category);
        List<Ad> GetAdsByUserId(int user_id);
        Ad GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        int AddNewAd(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id);
        int EditAd(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id);

    }
}
