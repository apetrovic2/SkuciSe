using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class UserHelper
    {
        public static List<UserModel> ConvertUsers(List<User> users)
        {
            var userModels = users.ConvertAll(user => new UserModel
            {
                id = user.id,
                username = user.username,
                password = user.password,
                name = user.name,
                email = user.email
            });
            return userModels;
        }
    }
}
