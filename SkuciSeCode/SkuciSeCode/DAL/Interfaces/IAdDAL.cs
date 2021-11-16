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
        Ad GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        Task<int> AddNewAd(Ad ad);
        int EditAd(Ad ad);
    }
}
