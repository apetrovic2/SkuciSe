using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class UserImage
    {
        public int id { get; set; }
        public int user_id { get; set; }
        public String image { get; set; }
       

        public UserImage()
        {

        }

        public UserImage(int id, int user_id, String image)
        {
            this.id = id;
            this.user_id = user_id;
            this.image = image;
        }

        public UserImage(int user_id, String image)
        {
            this.id = id;
            this.user_id = user_id;
            this.image = image;
        }
    }
}
