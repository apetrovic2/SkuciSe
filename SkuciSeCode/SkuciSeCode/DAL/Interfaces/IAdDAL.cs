using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace SkuciSeCode.DAL.Interfaces
{
    public interface IAdDAL
    {
        Task<List<AdWithImage>> GetAllAds(int category);
        Task<List<AdWithImage>> GetAdsByUserId(int user_id);
        Task<AdWithImage> GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        Task<int> AddNewAd(Ad ad);
        int EditAd(Ad ad);
        Task<List<AdWithImage>> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, String location, int internet, int ac, int heating, int tv);
        Task<int> SetAdPicture(int ad_id, String image);
        Task<int> MakeAnAppointment(int user_id, int ad_id, String date);
        Task<List<AppointmentInfo>> GetAppointmentByOwnerId(int id);
        Task<List<AppointmentInfo>> GetAppointmentResponse(int id);
        int ApproveAppointment(int app_id, int approve_status);
    }
}
