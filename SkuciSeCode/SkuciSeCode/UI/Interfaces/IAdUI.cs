using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI.Interfaces
{
    public interface IAdUI
    {
        int AddNewAd(String title, int flat_house, int sell_rent, String description, double size, String date_start, double price, String location);
    }
}
