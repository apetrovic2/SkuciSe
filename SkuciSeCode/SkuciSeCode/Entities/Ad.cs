using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class Ad
    {
        public int id { get; set; }
        public String title { get; set; }
        public int flat_house { get; set; }
        public int sell_rent { get; set; }
        public int number_of_rooms { get; set; }
        public String description { get; set; }
        public double size { get; set; }
        public DateTime date_start { get; set; }
        public DateTime? date_end { get; set; }
        public double price { get; set; }
        public String location { get; set; }
        public Ad()
        {

        }

        public Ad(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, double size, DateTime date_start, DateTime? date_end, double price, String location)
        {
            this.id = id;
            this.title = title;
            this.flat_house = flat_house;
            this.sell_rent = sell_rent;
            this.number_of_rooms = number_of_rooms;
            this.description = description;
            this.size = size;
            this.date_start = date_start;
            this.date_end = date_end;
            this.price = price;
            this.location = location;
        }

        public Ad(string title, int flat_house, int sell_rent, int number_of_rooms, string description, double size, DateTime date_start, DateTime? date_end, double price, String location)
        {
            
            this.title = title;
            this.flat_house = flat_house;
            this.sell_rent = sell_rent;
            this.number_of_rooms = number_of_rooms;
            this.description = description;
            this.size = size;
            this.date_start = date_start;
            this.date_end = date_end;
            this.price = price;
            this.location = location;
        }
    }
}
