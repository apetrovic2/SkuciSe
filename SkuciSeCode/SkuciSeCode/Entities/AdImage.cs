using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class AdImage
    {
        public int id { get; set; }
        public int ad_id { get; set; }
        public String image { get; set; }


        public AdImage()
        {

        }

        public AdImage(int id, int ad_id, String image)
        {
            this.id = id;
            this.ad_id = ad_id;
            this.image = image;
        }

        public AdImage(int ad_id, String image)
        {
            this.id = id;
            this.ad_id = ad_id;
            this.image = image;
        }
    }
}
