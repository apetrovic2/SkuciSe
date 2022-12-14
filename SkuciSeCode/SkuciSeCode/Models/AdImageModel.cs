using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class AdImageModel
    {
        public int id { get; set; }
        public int ad_id { get; set; }
        //public String image { get; set; }
        public String image { get; set; }

        [ForeignKey("ad_id")]
        public AdModel ad { get; set; }
    }
}
