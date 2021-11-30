using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class Appointment
    {
        public int id { get; set; }
        public int user_id { get; set; }
        public int ad_id { get; set; }
        public String date { get; set; }
        public int approved { get; set; }

        public Appointment()
        {

        }
        public Appointment(int id, int user_id, int ad_id, string date, int approved)
        {
            this.id = id;
            this.user_id = user_id;
            this.ad_id = ad_id;
            this.date = date;
            this.approved = approved;
        }

        public Appointment(int user_id, int ad_id, string date, int approved)
        {
            this.user_id = user_id;
            this.ad_id = ad_id;
            this.date = date;
            this.approved = approved;
        }
    }
}
