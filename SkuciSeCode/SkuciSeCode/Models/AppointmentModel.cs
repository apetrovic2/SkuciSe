using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class AppointmentModel
    {
        public int id { get; set; }
        public int user_id { get; set; }
        public int ad_id { get; set; }
        public String date { get; set; }
        public int approved { get; set; }

        [ForeignKey("ad_id")]
        public AdModel ad { get; set; }

        [ForeignKey("user_id")]
        public UserModel user { get; set; }
    }
}
