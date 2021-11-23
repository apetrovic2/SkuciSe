using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace SkuciSeCode.DAL.Interfaces
{
    public interface IAdDAL
    {
        List<Ad> GetAllAds(int category);
        List<Ad> GetAdsByUserId(int user_id);
        Ad GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        Task<int> AddNewAd(Ad ad);
        int EditAd(Ad ad);
        List<Ad> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, String location, int internet, int ac, int heating, int tv);
        Task<int> SetAdPicture(int ad_id, String image);
    }
}
