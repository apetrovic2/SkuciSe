﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class AdModel
    {
        public int id { get; set; }
        public String title { get; set; }
        public int flat_house { get; set; }
        public int sell_rent { get; set; }
        public int number_of_rooms { get; set; }
        public String description { get; set; }
        public float size { get; set; }
        public String date_start { get; set; }
        public String? date_end { get; set; }
        public float price { get; set; }
        public String location { get; set; }
    }
}
