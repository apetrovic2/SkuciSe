using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class UserImageHelper
    {
        public static List<UserImageModel> ConvertUsers(List<UserImage> userImages)
        {
            var userImageModels = userImages.ConvertAll(userImage => new UserImageModel
            {
                id = userImage.id,
                image = userImage.image,
                user_id = userImage.user_id,
            });
            return userImageModels;
        }
    }
}
