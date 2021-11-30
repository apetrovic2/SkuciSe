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
        public static List<AdWithImageModel> ConvertUsers(List<AdWithImage> adImages)
        {
            var adImageModels = adImages.ConvertAll(adImage => new AdWithImageModel
            {
                ad = adImage.ad,
                image = adImage.image
            });
            return adImageModels;
        }

        public static AdImageModel ConvertAdImage(AdImage adImage)
        {
            var adImageModel = new AdImageModel
            {
                id = adImage.id,
                ad_id = adImage.ad_id,
                image = adImage.image
            };
            return adImageModel;
        }
    }
}
