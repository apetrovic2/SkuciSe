using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class AdImageHelper
    {
        public static List<AdImageModel> ConvertUsers(List<AdImage> adImages)
        {
            var adImageModels = adImages.ConvertAll(adImage => new AdImageModel
            {
                id = adImage.id,
                image = adImage.image,
                ad_id = adImage.ad_id,
            });
            return adImageModels;
        }
    }
}
