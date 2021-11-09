using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class AdHelper
    {

        public static List<AdModel> ConvertUsers(List<Ad> ads)
        {
            var adModels = ads.ConvertAll(ad => new AdModel
            {
                id = ad.id,
                title = ad.title,
                flat_house = ad.flat_house,
                sell_rent = ad.sell_rent,
                number_of_rooms = ad.number_of_rooms,
                description = ad.description,
                size = ad.size,
                date_start = ad.date_start,
                date_end = ad.date_end,
                price = ad.price,
                location = ad.location
                
            });
            return adModels;
        }
    }
}
