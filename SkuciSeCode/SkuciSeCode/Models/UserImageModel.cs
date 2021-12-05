using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Models
{
    public class UserImageModel
    {
        public int id { get; set; }
        public int user_id { get; set; }
        public String image { get; set; }
        [ForeignKey("user_id")]
        public UserModel user { get; set; }
    }
}
