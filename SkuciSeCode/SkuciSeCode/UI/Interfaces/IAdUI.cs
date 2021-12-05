using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI.Interfaces
{
    public interface IAdUI
    {
        Task<List<AdWithImage>> GetAllAds(int category);
        Task<List<AdWithImage>> GetAdsByUserId(int user_id);
        Task<AdWithImage> GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        int AddNewAd(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id);
        int EditAd(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id);
        Task<List<AdWithImage>> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, String location, int internet, int ac, int heating, int tv);
        int SetAdPicture(int ad_id, String image);
        int MakeAnAppointment(int user_id, int ad_id, String date);
        Task<List<AppointmentInfo>> GetAppointmentByOwnerId(int id);
        Task<List<AppointmentInfo>> GetAppointmentResponse(int id);
        int ApproveAppointment(int app_id, int approve_status);
    }
}
