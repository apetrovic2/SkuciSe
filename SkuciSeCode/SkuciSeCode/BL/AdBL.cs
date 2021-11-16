﻿using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL
{
    public class AdBL : IAdBL
    {
        private readonly IAdDAL _iAdDAL;
        public AdBL(IAdDAL iAdDAL)
        {
            _iAdDAL = iAdDAL;
        }

        public List<Ad> GetAllAds(int category)
        {
            return _iAdDAL.GetAllAds(category);
        }

        public Ad GetAdById(int id)
        {
            return _iAdDAL.GetAdById(id);
        }

        public int AddNewAd(string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv)
        {
            Ad ad = new Ad(title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
            Task<int> ind =  _iAdDAL.AddNewAd(ad);
            int ind1 = ind.Result;
            return ind1;
        }

        public int CloseAd(int id, String date_end)
        {
            return _iAdDAL.CloseAd(id, date_end);
        }

        public int DeleteAd(int id)
        {
            return _iAdDAL.DeleteAd(id);
        }

        public int EditAd(int id, string title, int flat_house, int sell_rent, int number_of_rooms, string description, float size, string date_start, string date_end, float price, string location, int floor, int internet, int ac, int intercom, int garage, int elevator, int balcony, int yard, int heating, int tv)
        {
            Ad ad = new Ad(id, title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
            return _iAdDAL.EditAd(ad);
        }
    }
}
