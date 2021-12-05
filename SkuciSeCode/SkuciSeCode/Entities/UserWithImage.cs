using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class UserWithImage
    {
        public User user { get; set; }
        public String image { get; set; }

        public UserWithImage(User user, string image)
        {
            this.user = user;
            this.image = image;
        }
    }
}
