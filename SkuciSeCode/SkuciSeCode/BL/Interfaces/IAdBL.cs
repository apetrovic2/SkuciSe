using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL.Interfaces
{
    public interface IAdBL
    {
        int AddNewAd(String title, int flat_house, int sell_rent, String description, double size, DateTime date_start, double price, String location);

    }
}
