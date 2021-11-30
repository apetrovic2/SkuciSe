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
                location = ad.location,
                floor = ad.floor,
                internet = ad.internet,
                ac = ad.ac,
                intercom = ad.intercom,
                garage = ad.garage,
                elevator = ad.elevator,
                balcony = ad.balcony,
                yard = ad.yard,
                heating = ad.heating,
                tv = ad.tv,
                user_id = ad.user_id,

        });
            return adModels;
        }

        public static AdModel ConvertAd(Ad ad)
        {
            var adModels = new AdModel
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
                location = ad.location,
                floor = ad.floor,
                internet = ad.internet,
                ac = ad.ac,
                intercom = ad.intercom,
                garage = ad.garage,
                elevator = ad.elevator,
                balcony = ad.balcony,
                yard = ad.yard,
                heating = ad.heating,
                tv = ad.tv,
                user_id = ad.user_id,

            };
            return adModels;
        }
       }
}
