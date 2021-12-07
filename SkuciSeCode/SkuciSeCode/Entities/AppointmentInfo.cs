using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class AppointmentInfo
    {
        public int id { get; set; }
        public UserModel user { get; set; }
        public int approved { get; set; }
        public String date { get; set; }
        public int ad_id { get; set; }
        public String title { get; set; }
        public String owner_image { get; set; }

        public AppointmentInfo(int id, UserModel user, int approved, string date, int ad_id, String title)
        {
            this.user = user;
            this.approved = approved;
            this.date = date;
            this.ad_id = ad_id;
            this.title = title;
            this.id = id;
        }

        public AppointmentInfo(int id, UserModel user, int approved, string date, int ad_id, String title, String owner_image)
        {
            this.user = user;
            this.approved = approved;
            this.date = date;
            this.ad_id = ad_id;
            this.title = title;
            this.owner_image = owner_image;
            this.id = id;
        }
        public AppointmentInfo()
        {

        }
    }
}
