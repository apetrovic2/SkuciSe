using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI.Interfaces
{
    public interface IAdUI
    {
        List<Ad> GetAllAds(int category);
        Ad GetAdById(int id);
        int CloseAd(int id, String date_end);
        int DeleteAd(int id);
        int AddNewAd(String title, int flat_house, int sell_rent, String description, float size, String date_start, float price, String location);
    }
}
