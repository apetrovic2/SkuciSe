using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Helpers
{
    public class AppointmentHelper
    {
        public static List<AppointmentModel> ConvertAppointments(List<Appointment> apps)
        {
            var appModels = apps.ConvertAll(app => new AppointmentModel
            {
                id = app.id,
                user_id = app.user_id,
                ad_id = app.ad_id,
                date = app.date,
                approved = app.approved
            });
            return appModels;
        }

        public static AppointmentModel ConvertAppointment(Appointment app)
        {
            var appModel = new AppointmentModel
            {
                id = app.id,
                user_id = app.user_id,
                ad_id = app.ad_id,
                date = app.date,
                approved = app.approved
            };
            return appModel;
        }
    }
}
