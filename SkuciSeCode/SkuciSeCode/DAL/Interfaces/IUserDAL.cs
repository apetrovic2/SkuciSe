using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL.Interfaces
{
    public interface IUserDAL
    {
        List<User> GetAllUsers();
        int Registration(User user);
        int Login(String username, String password);
    }
}
