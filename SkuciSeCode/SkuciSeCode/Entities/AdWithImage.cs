using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class AdWithImage
    {
        public Ad ad { get; set; }
        public String image { get; set; }

        public AdWithImage(Ad ad, String image)
        {
            this.ad = ad;
            this.image = image;
        }
    }
}
