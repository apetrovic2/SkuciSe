using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class PasswordHelper
    {
        public static Boolean isValidPassword(User user, String password)
        {
            var saltBytes = Convert.FromBase64String(user.salt);
            var rfc = new Rfc2898DeriveBytes(password, saltBytes, 10000);
            String passwordHash = Convert.ToBase64String(rfc.GetBytes(256));

            if (user.hash == passwordHash)
            {
                return true;
            }

            return false;
        }

        public static String getNewSalt(ref byte[] saltBytes)
        {
            var provider = new RNGCryptoServiceProvider();
            provider.GetNonZeroBytes(saltBytes);

            var salt = Convert.ToBase64String(saltBytes);

            return salt;
        }

        public static String getPasswordHash(String password, byte[] saltBytes)
        {
            var rfc = new Rfc2898DeriveBytes(password, saltBytes, 10000);
            var hash = Convert.ToBase64String(rfc.GetBytes(256));

            return hash;
        }
    }
}
