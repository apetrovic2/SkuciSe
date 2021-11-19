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
        public float size { get; set; }
        public String date_start { get; set; }
        public String? date_end { get; set; }
        public float price { get; set; }
        public String location { get; set; }
        public int floor { get; set; }
        public int internet { get; set; }
        public int ac { get; set; }
        public int intercom { get; set; }
        public int garage { get; set; }
        public int elevator { get; set; }
        public int balcony { get; set; }
        public int yard { get; set; }
        public int heating { get; set; }
        public int tv { get; set; }
        public int user_id { get; set; }
        public Ad()
        {

        }

        public Ad(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id)
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
            this.floor = floor;
            this.internet = internet;
            this.ac = ac;
            this.intercom = intercom;
            this.garage = garage;
            this.elevator = elevator;
            this.balcony = balcony;
            this.yard = yard;
            this.heating = heating;
            this.tv = tv;
            this.user_id = user_id;
        }

        public Ad(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv, int user_id)
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
            this.floor = floor;
            this.internet = internet;
            this.ac = ac;
            this.intercom = intercom;
            this.garage = garage;
            this.elevator = elevator;
            this.balcony = balcony;
            this.yard = yard;
            this.heating = heating;
            this.tv = tv;
            this.user_id = user_id;
        }
    }
}
