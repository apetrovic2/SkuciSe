using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class AppointmentInfoModel
    {
        public UserModel user { get; set; }
        public int approved { get; set; }
        public String date { get; set; }
        public int ad_id { get; set; }

    }
}
