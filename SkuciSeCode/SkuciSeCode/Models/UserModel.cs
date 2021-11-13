using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class UserModel
    {
        public int id { get; set; }
        public String username { get; set; }
        public String hash { get; set; }
        public String salt { get; set; }
        public String name { get; set; }
        public String email { get; set; }

    }
}
