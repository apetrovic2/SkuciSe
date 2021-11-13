using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace SkuciSeCode.DAL
{
    public class UserDAL : IUserDAL
    {
        private readonly UserDbContext _context;
        public UserDAL(UserDbContext context)
        {
            _context = context;
        }
        public List<User> GetAllUsers()
        {
            var list = _context.Users.ToList();
            return list;
        }

        public User GetUserById(int id)
        {
            var users = _context.Users.ToList().Where(u => u.id == id);
            User user = users.FirstOrDefault();
            return user;
        }

        public int Login(string username, string password)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(u => u.username.Equals(username));
            var user = users.FirstOrDefault();
            if(user != null)
            {
                /*if (!user.password.Equals(password))
                {
                    ind = -3;
                }
                else
                {
                    ind = user.id;
                }*/
                var saltBytes = Convert.FromBase64String(user.salt);
                var rfc = new Rfc2898DeriveBytes(password, saltBytes, 10000);
                String passwordHash = Convert.ToBase64String(rfc.GetBytes(256));
                if(user.hash == passwordHash)
                {
                    ind = user.id;
                }
                else
                {
                    ind = -3;
                }
            }  
            return ind;
            
        }

        public async Task<int> RegistrationAsync(User user)
        {
            int ind = -3;
            if(!_context.Users.Any(u => u.email.Equals(user.email)))
            {
                if (!_context.Users.Any(u => u.username.Equals(user.username)))
                {
                    await _context.Users.AddAsync(user);
                    ind = _context.SaveChanges();
                }
                else
                {
                    ind = -1;
                }
            }
            
            return ind;
        }

        public int EditUser(int id, String username, String password, String name, String email)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(user => user.id == id);
            User user = users.FirstOrDefault();
            if (user != null)
            {
                user.username = username;
                //user.password = password;
                user.name = name;
                user.email = email;
                ind = _context.SaveChanges();
            }
            return ind;
        }

        public int DeleteUser(int id)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(user => user.id == id);
            User user = users.FirstOrDefault();

            if (user != null)
            {
                _context.Users.Remove(user);
                ind = _context.SaveChanges();
            }

            return ind;
        }

    }
}
