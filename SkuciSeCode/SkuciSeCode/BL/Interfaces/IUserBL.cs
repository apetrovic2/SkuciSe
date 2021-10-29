﻿using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL.Interfaces
{
    public interface IUserBL
    {
        List<User> GetAllUsers();
        int Registration(String username, String password, String name, String email);
        int Login(String username, String password);
    }
}
